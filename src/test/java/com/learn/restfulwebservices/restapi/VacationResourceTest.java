package com.learn.restfulwebservices.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.restfulwebservices.restapi.pojo.Vacation;
import com.learn.restfulwebservices.restapi.service.VacationService;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(VacationResource.class)
public class VacationResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacationService vacationService;

    private static final ObjectMapper mapper = new ObjectMapper();


    public VacationResourceTest() {
    }

    @Test
    public void testWhenVacationIDdAndDataExistsThenReturnHttp200andResponseContent() throws Exception {

        final long vacationID = 534l;

        final FileInputStream fileInputStream = new FileInputStream(ResourceUtils.getFile("classpath:vacaion_response_http200.json"));
        final String staticResponse = StreamUtils.copyToString(fileInputStream, Charset.defaultCharset());

        Vacation mockInvoiceResponse = mapper.readValue(staticResponse, Vacation.class);

        when(vacationService.retrieveVacation(vacationID)).thenReturn(mockInvoiceResponse);

        MvcResult result = mockMvc.perform(get("/vacations/{id}", vacationID)).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        Vacation actualVacation = mapper.readValue(result.getResponse().getContentAsString(), Vacation.class);
        MatcherAssert.assertThat(actualVacation, is(samePropertyValuesAs(mockInvoiceResponse)));

    }

    @Test
    public void testUpdateAndPutResponseWithNoContent() throws Exception {
        final FileInputStream fileInputStream = new FileInputStream(ResourceUtils.getFile("classpath:updateVacation.json"));
        final String updateVactionInJsonfmt = StreamUtils.copyToString(fileInputStream, Charset.defaultCharset());
        final long mockVactionID = 1L;
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/vacations/" + mockVactionID)
                .accept(MediaType.APPLICATION_JSON).content(updateVactionInJsonfmt)
                .contentType(MediaType.APPLICATION_JSON);


        when(vacationService.updateVacation(ArgumentMatchers.any(Vacation.class), anyLong())).thenReturn(ResponseEntity.noContent().build());

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
