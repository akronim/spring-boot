package com.example.lombok.builder;

import lombok.Value;

@Value
final class ImmutableClient {

    private int id;
    private String name;

}
