package com.employee.Employee.Management.Portal.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ApiResponseDtoTest {

    @Test
    void testDefaultConstructorAndSetters() {
        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setMessage("Test message");

        assertThat(apiResponseDto.getMessage()).isEqualTo("Test message");
    }

    @Test
    void testParameterizedConstructor() {
        ApiResponseDto apiResponseDto = new ApiResponseDto("Test message");

        assertThat(apiResponseDto.getMessage()).isEqualTo("Test message");
    }

    @Test
    void testEquals() {
        ApiResponseDto apiResponseDto1 = new ApiResponseDto("Test message");
        ApiResponseDto apiResponseDto2 = new ApiResponseDto("Test message");
        ApiResponseDto apiResponseDto3 = new ApiResponseDto("Another message");

        assertThat(apiResponseDto1).isEqualTo(apiResponseDto2);
        assertThat(apiResponseDto1).isNotEqualTo(apiResponseDto3);
    }

    @Test
    void testHashCode() {
        ApiResponseDto apiResponseDto1 = new ApiResponseDto("Test message");
        ApiResponseDto apiResponseDto2 = new ApiResponseDto("Test message");
        ApiResponseDto apiResponseDto3 = new ApiResponseDto("Another message");

        assertThat(apiResponseDto1.hashCode()).isEqualTo(apiResponseDto2.hashCode());
        assertThat(apiResponseDto1.hashCode()).isNotEqualTo(apiResponseDto3.hashCode());
    }

    @Test
    void testToString() {
        ApiResponseDto apiResponseDto = new ApiResponseDto("Test message");

        assertThat(apiResponseDto.toString()).isEqualTo("ApiResponseDto{message='Test message'}");
    }
}
