package com.pibicloud.io;

@FunctionalInterface
public interface ProcessContextFactory {
	ProcessContext apply();
}
