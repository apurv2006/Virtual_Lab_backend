package virtual.lab.string_backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class StringControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetStringLength() throws Exception {
        mockMvc.perform(get("/length").param("input", "HelloWorld"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    public void testGetSubstring() throws Exception {
        mockMvc.perform(get("/substring")
                        .param("input", "HelloWorld")
                        .param("beginIndex", "0")
                        .param("endIndex", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    public void testConvertToUppercase() throws Exception {
        mockMvc.perform(get("/uppercase").param("input", "HelloWorld"))
                .andExpect(status().isOk())
                .andExpect(content().string("HELLOWORLD"));
    }

    @Test
    public void testConvertToLowercase() throws Exception {
        mockMvc.perform(get("/lowercase").param("input", "HelloWorld"))
                .andExpect(status().isOk())
                .andExpect(content().string("helloworld"));
    }

    @Test
    public void testReverseString() throws Exception {
        mockMvc.perform(get("/reverse").param("input", "HelloWorld"))
                .andExpect(status().isOk())
                .andExpect(content().string("dlroWolleH"));
    }

    @Test
    public void testConcatenateStrings() throws Exception {
        mockMvc.perform(get("/concat")
                        .param("input1", "Hello")
                        .param("input2", "World"))
                .andExpect(status().isOk())
                .andExpect(content().string("HelloWorld"));
    }

    @Test
    public void testCheckContains() throws Exception {
        mockMvc.perform(get("/contains")
                        .param("input", "HelloWorld")
                        .param("sequence", "World"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testReplaceString() throws Exception {
        mockMvc.perform(get("/replace")
                        .param("input", "HelloWorld")
                        .param("target", "World")
                        .param("replacement", "Universe"))
                .andExpect(status().isOk())
                .andExpect(content().string("HelloUniverse"));
    }

    @Test
    public void testSplitString() throws Exception {
        mockMvc.perform(get("/split")
                        .param("input", "Hello,World,Java")
                        .param("delimiter", ","))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"Hello\",\"World\",\"Java\"]"));
    }
    @Test public void testCountOccurrences() throws Exception { 
mockMvc.perform(get("/count") .param("input", "HelloWorld") 
.param("target", "o")) .andExpect(status().isOk())
 .andExpect(content().string("2")); 
}
 @Test public void testIsPalindrome() throws Exception
  { mockMvc.perform(get("/ispalindrome").param("input", "A man a plan a canal Panama")) 
  .andExpect(status().isOk()) .andExpect(content().string("true"));
 } 
 @Test public void testFindLongestWord() throws Exception 
 { mockMvc.perform(get("/longestword").param("input", "The quick brown fox jumped over the lazy dog")) 
 .andExpect(status().isOk()) .andExpect(content().string("jumped")); }
}
