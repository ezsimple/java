package net.ion.nipa.controller;

import java.util.Optional;

public abstract class AbstractController {

	public String viewPath(Optional<String> path, Optional<String> page) {
		final String startPage = "index";
		String target = startPage;

		if (path.isPresent() && page.isPresent()) {
			target = path.get() + "/" + page.get();
		}

		if (path.isPresent() && !page.isPresent()) {
			target = path.get() + "/" + startPage;
		}

		if (!path.isPresent() && page.isPresent()) {
			target = page.get();
		}

		if (!path.isPresent() && !page.isPresent()) {
			target = "home";
		}
		
		return target;
	}

}
