package br.com.hyperclass.caixaeletronico.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.DefaultComparator;

public class EventoComparator extends DefaultComparator {
	
	private final String[] ignoredTerms;

	public EventoComparator(final JSONCompareMode mode, final String... ignoredTerms) {
		super(mode);
		this.ignoredTerms = ignoredTerms;
	}
	
	public EventoComparator(final String... ignoredTerms) {
		this(JSONCompareMode.STRICT, ignoredTerms);
	}
	
	/**
	 * O método override<code>compareValues</code> ignora atributos json do tipo Date
	 */
	@Override
	public void compareValues(String prefix, Object expectedValue, Object actualValue, JSONCompareResult result) throws JSONException {
		if (expectedValue.getClass().isAssignableFrom(JSONObject.class)) {
			super.compareValues(prefix, expectedValue, actualValue, result);
		} else {
			boolean found = false;
			for (final String term : ignoredTerms) {
				if (prefix.endsWith("." + term)) {
					result.passed();
					found = true;
					break;
				}
			}
			if (!found) {
				super.compareValues(prefix, expectedValue, actualValue, result);
			}
		}
	}
}
