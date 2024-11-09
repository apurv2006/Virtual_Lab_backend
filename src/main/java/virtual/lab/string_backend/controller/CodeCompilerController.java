package virtual.lab.string_backend.controller;

import org.springframework.web.bind.annotation.*;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/compile")
public class CodeCompilerController {

    @PostMapping
    public String compileAndExecuteCode(@RequestBody CodeRequest codeRequest) {
        if (codeRequest == null) {
            return "Request body is missing or incorrectly formatted.";
        }

        String code = codeRequest.getCode();
        String result = compileAndRun(code);
        return result;
    }

    private String compileAndRun(String code) {
        // Define the class name
        String className = "Temp";

        // Create a Java source file in-memory
        JavaFileObject file = new InMemoryJavaFileObject(className, code);

        // Get the system Java compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            return "JDK is not installed or not properly configured.";
        }

        // Set up the in-memory file manager
        JavaFileManager fileManager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null));

        // Compile the source code
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, Collections.singletonList(file));
        boolean success = task.call();
        if (!success) {
            return "Compilation failed.";
        }

        // Load the compiled class
        try {
            ClassLoader classLoader = fileManager.getClassLoader(null);
            Class<?> cls = classLoader.loadClass(className);
            Method main = cls.getMethod("main", String[].class);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream old = System.out;
            System.setOut(ps);

            // Execute the compiled class's main method
            main.invoke(null, (Object) new String[]{});

            System.out.flush();
            System.setOut(old);
            return baos.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Execution failed: " + e.getMessage();
        }
    }

    // Inner class for CodeRequest
    public static class CodeRequest {
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    // Inner class for in-memory Java file
    public static class InMemoryJavaFileObject extends SimpleJavaFileObject {
        private final String code;

        public InMemoryJavaFileObject(String className, String code) {
            super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    // Inner class for in-memory file manager
    public static class InMemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {
        public InMemoryFileManager(JavaFileManager fileManager) {
            super(fileManager);
        }

        @Override
        public ClassLoader getClassLoader(Location location) {
            return new DynamicClassLoader();
        }
    }

    // Inner class for dynamic class loader
    public static class DynamicClassLoader extends ClassLoader {
        public DynamicClassLoader() {
            super(ClassLoader.getSystemClassLoader());
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] b = loadClassData(name);
                return defineClass(name, b, 0, b.length);
            } catch (IOException e) {
                throw new ClassNotFoundException(name);
            }
        }

        private byte[] loadClassData(String name) throws IOException {
            InputStream is = getClass().getClassLoader().getResourceAsStream(name.replace('.', '/') + ".class");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch;
            while ((ch = is.read()) != -1) {
                baos.write(ch);
            }
            return baos.toByteArray();
        }
    }
}
