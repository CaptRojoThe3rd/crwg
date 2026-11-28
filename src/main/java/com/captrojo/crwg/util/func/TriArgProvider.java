package com.captrojo.crwg.util.func;

@FunctionalInterface
public interface TriArgProvider<A, B, C, R>
{
	R get(A a, B b, C c);
}
