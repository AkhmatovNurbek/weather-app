package uz.atmos.weatheratmos.controller;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public abstract class ApiController<S> {
    protected final S service;
    protected static final String API = "/api";
    protected static final String V1 = "/v1";
    protected static final String ADMIN = "/admin";
    protected static final String STAFF = "/staff";
    protected static final String PATH =API + V1;
}
