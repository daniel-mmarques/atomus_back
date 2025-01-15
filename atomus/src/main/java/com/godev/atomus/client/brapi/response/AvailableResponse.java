package com.godev.atomus.client.brapi.response;

import java.util.List;

public record AvailableResponse(
        List<String> stocks
) {}