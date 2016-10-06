package br.com.hyperclass.caixaeletronico.util;

import java.sql.Date;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.DefaultComparator;

public class EventoComparator extends DefaultComparator {

	public EventoComparator(final JSONCompareMode mode) {
		super(mode);
	}
	
	public EventoComparator() {
		super(JSONCompareMode.STRICT);
	}
	
	/**
	 * O método override<code>compareValues</code> ignora atributos json do tipo Date
	 */
	@Override
	public void compareValues(String prefix, Object expectedValue, Object actualValue, JSONCompareResult result) throws JSONException {
		if(expectedValue.getClass().isAssignableFrom(Date.class)){
			result.passed();
		} else {
			super.compareValues(prefix, expectedValue, actualValue, result);
		}
	}
}
