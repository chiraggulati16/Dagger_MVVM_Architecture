package com.example.dagger_mvvm_architecture.di;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import dagger.MapKey;

@MapKey
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}