package com.nv.soft.nvsoftuser.modal;


public record RegisterRequest(
        String name,
        String email,
        String password
) {}
