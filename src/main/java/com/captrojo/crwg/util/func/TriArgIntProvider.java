package com.captrojo.crwg.util.func;

@FunctionalInterface
public interface TriArgIntProvider<A, B, C>
{
	int getInt(A a, B b, C c);
}
