package com.project.locate.common.logging;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.ext.LoggerWrapper;

import net.logstash.logback.argument.StructuredArgument;
import net.logstash.logback.argument.StructuredArguments;

/**
 * A custom implementation of a {@link org.slf4j.Logger} which delegates to
 * Logback for all the generic SLF4J logging methods. The extension comes into
 * play when we want to add specific logging details to every log message such
 * as allowing messages which are written as key/value pairs which can be picked
 * up and queried in Splunk or adding markers for log messages which may contain
 * confidential information.
 *
 *
 */
public class ProjectLogger implements org.slf4j.Logger {

	private static final String DEBUG = "debug";
	private static final String INFO = "info";
	private static final String ERROR = "error";
	private static final String WARN = "warn";
	private static final String TRACE = "trace";
	private static final String VALUE = "value";
	private static final Integer GAP = 2;
	private static final Integer INCREMENT = 1;

	public static final String ERROR_DETAIL = "error-detail";
	public static final String STACK_TRACE = "stack-trace";

	private final LoggerWrapper delegate;

	private ProjectLogger(final org.slf4j.Logger delegate) { // NOSONAR
		this.delegate = new LoggerWrapper(delegate, delegate.getName());
	}

	public static ProjectLogger get(final Class<?> c) {
		org.slf4j.Logger delegate = LoggerFactory.getLogger(c);
		return new ProjectLogger(delegate);
	}

	private Supplier<StructuredArgument> kv(final Object... values) {
		return () -> {
			if (values == null || values.length == 0) {
				return StructuredArguments.e(Collections.emptyMap());
			}
			Map<String, Object> map = new LinkedHashMap<>();

			int i = 0;
			while (i < values.length) {
				Object value = values.length > i + INCREMENT ? values[i + INCREMENT] : null;
				if (value instanceof Supplier) {
					value = Supplier.class.cast(value).get();
				}
				String key = values[i] == null ? Integer.toString(i) : String.valueOf(values[i]);
				map.put(key, value);
				i += GAP;
			}
			return StructuredArguments.entries(map);
		};
	}

	@SuppressWarnings("unchecked")
	public void info(final String message, final Supplier<StructuredArgument>... arguments) {
		if (this.isInfoEnabled()) {
			List<StructuredArgument> sa = new ArrayList<>();
			for (Supplier<StructuredArgument> a : arguments) {
				sa.add(a.get());
			}
			this.info(message, sa.toArray());
		}
	}

	private Object resolve(final Object source) {
		return source instanceof Supplier ? Supplier.class.cast(source).get() : source;
	}

	private Object[] resolveAll(final Object... sources) {
		List<Object> container = new ArrayList<>();
		for (Object s : sources) {
			container.add(this.resolve(s));
		}
		return container.toArray();
	}

	private Map<String, Object> resolveAllMap(final Object... values) {
		if (values == null || values.length == 0) {
			return Collections.emptyMap();
		}

		Map<String, Object> map = new LinkedHashMap<>();

		int i = 0;
		while (i < values.length) {
			Object value = values.length > i + INCREMENT ? values[i + INCREMENT] : null;
			if (value instanceof Supplier) {
				value = Supplier.class.cast(value).get();
			}
			String key = values[i] == null ? Integer.toString(i) : String.valueOf(values[i]);
			map.put(key, value);
			i += GAP;
		}
		return map;

	}

	public void metric(final String name, final Object value) {
		this.info("{}", kv("type", "METRIC", "name", name, VALUE, value));
	}

	@Override
	public void error(final String format, final Object arg) {
		if (this.isErrorEnabled()) {
			this.delegate.error(format, this.resolve(arg));
		}
	}

	public void debug(final String label, final Map<String, Object> arg) {
		if (isDebugEnabled()) {
			writeMessage(DEBUG, label, arg);
		}
	}

	public void info(final String label, final Map<String, Object> arg) {
		if (isInfoEnabled()) {
			writeMessage(INFO, label, arg);
		}
	}

	public void error(final String label, final Map<String, Object> arg) {
		if (isErrorEnabled()) {
			writeMessage(ERROR, label, arg);
		}
	}

	public void trace(final String label, final Map<String, Object> arg) {
		if (isTraceEnabled()) {
			writeMessage(TRACE, label, arg);
		}
	}

	public void warn(final String label, final Map<String, Object> arg) {
		if (isWarnEnabled()) {
			writeMessage(WARN, label, arg);
		}
	}

	private void writeMessage(String type, final String errContent, final Map<String, Object> arg) {

		if (arg == null) {
			return;
		}

		for (Map.Entry<String, Object> entry : arg.entrySet()) {
			if (entry.getValue() != null) {
				if (!(entry.getValue().toString()).startsWith("\"")) {
					entry.setValue(embellish(entry.getValue().toString()));
				}
			}
		}
		printer(type, errContent, arg);

	}

	private void printer(String type, String content, final Map<String, Object> arg) {
		switch (type) {
		case ERROR:
			this.error("{}", kv(type, embellish(content), VALUE, arg));
			break;
		case DEBUG:
			this.debug("{}", kv(type, embellish(content), VALUE, arg));
			break;
		case WARN:
			this.warn("{}", kv(type, embellish(content), VALUE, arg));
			break;
		case TRACE:
			this.trace("{}", kv(type, embellish(content), VALUE, arg));
			break;
		case INFO:
		default:
			this.info("{}", kv(type, embellish(content), VALUE, arg));
			break;
		}
	}

	private String embellish(String strUnformatted) {
		return ("\"" + strUnformatted + "\"");
	}

	@Override
	public String getName() {
		return this.delegate.getName();
	}

	@Override
	public boolean isTraceEnabled() {
		return this.delegate.isTraceEnabled();
	}

	@Override
	public void trace(final String msg) {
		this.delegate.trace(msg);
	}

	@Override
	public void trace(final String format, final Object arg) {
		if (this.isTraceEnabled()) {
			this.trace(format, this.resolve(arg));
		}
	}

	@Override
	public void trace(final String format, final Object arg1, final Object arg2) {
		if (this.isTraceEnabled()) {
			this.delegate.trace(format, resolve(arg1), resolve(arg2));
		}
	}

	@Override
	public void trace(final String msg, final Throwable t) {
		this.delegate.trace(msg, t);
	}

	@Override
	public boolean isTraceEnabled(final Marker marker) {
		return this.delegate.isTraceEnabled(marker);
	}

	@Override
	public void trace(final Marker marker, final String msg) {
		this.delegate.trace(marker, msg);
	}

	@Override
	public void trace(final Marker marker, final String format, final Object arg) {
		if (this.isTraceEnabled()) {
			this.delegate.trace(marker, format, resolve(arg));
		}
	}

	@Override
	public void trace(final Marker marker, final String format, final Object arg1, final Object arg2) {
		if (this.isTraceEnabled()) {
			this.delegate.trace(marker, format, resolve(arg1), resolve(arg2));
		}
	}

	@Override
	public void trace(final Marker marker, final String format, final Object... argArray) {
		if (this.isTraceEnabled()) {
			this.delegate.trace(marker, format, this.resolveAll(argArray));
		}
	}

	@Override
	public void trace(final Marker marker, final String msg, final Throwable t) {
		this.delegate.trace(marker, msg, t);
	}

	@Override
	public boolean isDebugEnabled() {
		return this.delegate.isDebugEnabled();
	}

	@Override
	public void debug(final String msg) {
		this.delegate.debug(msg);
	}

	@Override
	public void debug(final String format, final Object arg) {
		if (this.isDebugEnabled()) {
			this.delegate.debug(format, this.resolve(arg));
		}
	}

	@Override
	public void debug(final String format, final Object arg1, final Object arg2) {
		if (this.isDebugEnabled()) {
			this.delegate.debug(format, this.resolve(arg1), this.resolve(arg2));
		}
	}

	@Override
	public void debug(final String msg, final Throwable t) {
		this.delegate.debug(msg, t);
	}

	@Override
	public boolean isDebugEnabled(final Marker marker) {
		return this.delegate.isDebugEnabled(marker);
	}

	@Override
	public void debug(final Marker marker, final String msg) {
		this.delegate.debug(marker, msg);
	}

	@Override
	public void debug(final Marker marker, final String format, final Object arg) {
		if (this.isDebugEnabled()) {
			this.delegate.debug(marker, format, this.resolve(arg));
		}
	}

	@Override
	public void debug(final Marker marker, final String format, final Object arg1, final Object arg2) {
		if (this.isDebugEnabled()) {
			this.delegate.debug(marker, format, this.resolve(arg1), this.resolve(arg2));
		}
	}

	@Override
	public void debug(final Marker marker, final String format, final Object... arguments) {
		if (this.isDebugEnabled()) {
			this.delegate.debug(marker, format, this.resolveAll(arguments));
		}
	}

	@Override
	public void debug(final Marker marker, final String msg, final Throwable t) {
		this.delegate.debug(marker, msg, t);
	}

	@Override
	public boolean isInfoEnabled() {
		return this.delegate.isInfoEnabled();
	}

	@Override
	public void info(final String msg) {
		this.delegate.info(msg);
	}

	@Override
	public void info(final String format, final Object arg) {
		if (this.isInfoEnabled()) {
			this.delegate.info(format, this.resolve(arg));
		}
	}

	@Override
	public void info(final String format, final Object arg1, final Object arg2) {
		if (this.isInfoEnabled()) {
			this.delegate.info(format, this.resolve(arg1), this.resolve(arg2));
		}
	}

	@Override
	public void info(final String msg, final Throwable t) {
		this.delegate.info(msg, t);
	}

	@Override
	public boolean isInfoEnabled(final Marker marker) {
		return this.delegate.isInfoEnabled(marker);
	}

	@Override
	public void info(final Marker marker, final String msg) {
		this.delegate.info(marker, msg);
	}

	@Override
	public void info(final Marker marker, final String format, final Object arg) {
		if (this.isInfoEnabled()) {
			this.delegate.info(marker, format, this.resolve(arg));
		}
	}

	@Override
	public void info(final Marker marker, final String format, final Object arg1, final Object arg2) {
		if (this.isInfoEnabled()) {
			this.delegate.info(marker, format, this.resolve(arg1), this.resolve(arg2));
		}
	}

	@Override
	public void info(final Marker marker, final String format, final Object... arguments) {
		if (this.isInfoEnabled()) {
			this.delegate.info(marker, format, this.resolve(arguments));
		}
	}

	@Override
	public void info(final Marker marker, final String msg, final Throwable t) {
		this.delegate.info(marker, msg, t);
	}

	@Override
	public boolean isWarnEnabled() {
		return this.delegate.isWarnEnabled();
	}

	@Override
	public void warn(final String msg) {
		this.delegate.warn(msg);
	}

	@Override
	public void warn(final String format, final Object arg) {
		if (this.isWarnEnabled()) {
			this.delegate.warn(format, this.resolve(arg));
		}
	}

	@Override
	public void warn(final String format, final Object arg1, final Object arg2) {
		if (this.isWarnEnabled()) {
			this.delegate.warn(format, this.resolve(arg1), this.resolve(arg2));
		}
	}

	@Override
	public void warn(final String msg, final Throwable t) {
		this.delegate.warn(msg, t);
	}

	@Override
	public boolean isWarnEnabled(final Marker marker) {
		return this.delegate.isWarnEnabled(marker);
	}

	@Override
	public void warn(final Marker marker, final String msg) {
		this.delegate.warn(marker, msg);
	}

	@Override
	public void warn(final Marker marker, final String format, final Object arg) {
		if (this.isWarnEnabled()) {
			this.delegate.warn(marker, format, this.resolve(arg));
		}
	}

	@Override
	public void warn(final Marker marker, final String format, final Object arg1, final Object arg2) {
		if (this.isWarnEnabled()) {
			this.delegate.warn(marker, format, this.resolve(arg1), this.resolve(arg2));
		}
	}

	@Override
	public void warn(final Marker marker, final String format, final Object... arguments) {
		if (this.isWarnEnabled()) {
			this.delegate.warn(marker, format, this.resolve(arguments));
		}
	}

	@Override
	public void warn(final Marker marker, final String msg, final Throwable t) {
		this.delegate.warn(marker, msg, t);
	}

	@Override
	public boolean isErrorEnabled() {
		return this.delegate.isErrorEnabled();
	}

	@Override
	public void error(final String msg) {
		this.delegate.error(msg);
	}

	@Override
	public void error(final String format, final Object arg1, final Object arg2) {
		if (this.isErrorEnabled()) {
			this.delegate.error(format, this.resolve(arg1), this.resolve(arg2));
		}
	}

	@Override
	public void debug(final String format, final Object... arguments) {
		if (this.isDebugEnabled()) {
			Map<String, Object> logEvents = this.resolveAllMap(arguments);
			this.writeMessage(DEBUG, format, logEvents);
		}
	}

	@Override
	public void info(final String format, final Object... arguments) {
		if (this.isInfoEnabled()) {
			Map<String, Object> logEvents = this.resolveAllMap(arguments);
			this.writeMessage(INFO, format, logEvents);
		}
	}

	@Override
	public void error(final String format, final Object... arguments) {
		if (this.isErrorEnabled()) {
			Map<String, Object> logEvents = this.resolveAllMap(arguments);
			this.writeMessage(ERROR, format, logEvents);
		}
	}

	@Override
	public void warn(final String format, final Object... arguments) {
		if (this.isWarnEnabled()) {
			Map<String, Object> logEvents = this.resolveAllMap(arguments);
			this.writeMessage(WARN, format, logEvents);
		}
	}

	@Override
	public void trace(final String format, final Object... arguments) {
		if (this.isTraceEnabled()) {
			Map<String, Object> logEvents = this.resolveAllMap(arguments);
			this.writeMessage(TRACE, format, logEvents);
		}
	}

	@Override
	public void error(final String msg, final Throwable t) {
		this.delegate.error(msg, t);
	}

	@Override
	public boolean isErrorEnabled(final Marker marker) {
		return this.delegate.isErrorEnabled(marker);
	}

	@Override
	public void error(final Marker marker, final String msg) {
		this.delegate.error(marker, msg);
	}

	@Override
	public void error(final Marker marker, final String format, final Object arg) {
		if (this.isErrorEnabled()) {
			this.delegate.error(marker, format, this.resolve(arg));
		}
	}

	@Override
	public void error(final Marker marker, final String format, final Object arg1, final Object arg2) {
		if (this.isErrorEnabled()) {
			this.delegate.error(marker, format, this.resolve(arg1), this.resolve(arg2));
		}
	}

	@Override
	public void error(final Marker marker, final String format, final Object... arguments) {
		if (this.isErrorEnabled()) {
			this.delegate.error(marker, format, this.resolveAll(arguments));
		}
	}

	@Override
	public void error(final Marker marker, final String msg, final Throwable t) {
		this.delegate.error(marker, msg, t);
	}
}
