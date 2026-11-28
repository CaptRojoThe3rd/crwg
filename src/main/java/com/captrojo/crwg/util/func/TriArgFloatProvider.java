package com.captrojo.crwg.util.func;

@FunctionalInterface
public interface TriArgFloatProvider<A, B, C>
{
	float getFloat(A a, B b, C c);
}
