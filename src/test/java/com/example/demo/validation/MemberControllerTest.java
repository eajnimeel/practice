package com.example.demo.validation;

import com.example.demo.logger.LoggerConfig;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@Import(LoggerConfig.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    private String url = "/validation/member";

    @Test
    public void 등록_실패_테스트() throws Exception {

        mockMvc.perform(post(url))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 등록_성공_테스트() throws Exception {
        MockHttpServletRequestBuilder builder = post(url)
                .param("loginId", "minjae")
                .param("memberNm", "이민재")
                .param("email", "minjae@live.com")
                .param("mobile", "01095668006");

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void 수정_실패_테스트() throws Exception {
        MockHttpServletRequestBuilder builder = put(url)
                .param("loginId", "minjae")
                .param("memberNm", "이민재")
                .param("email", "minjae@live.com")
                .param("mobile", "01095668006");

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void 수정_성공_테스트() throws Exception {
        MockHttpServletRequestBuilder builder = put(url)
                .param("memberNo", "1")
                .param("loginId", "minjae")
                .param("memberNm", "이민재")
                .param("email", "minjae@live.com")
                .param("mobile", "01095668006");

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
    }

}
