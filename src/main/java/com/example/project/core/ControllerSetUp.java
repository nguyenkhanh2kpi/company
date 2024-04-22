package com.example.project.core;

@FunctionalInterface
interface ControllerSetUp<T> {
    void setup(T controller);
}