function _isObject(obj) {
  return (
    obj !== null &&
    typeof obj === 'object' &&
    Object.prototype.toString.call(obj) !== '[object Array]'
  );
}

export function assertOptions(options) {
  const { config, init, onReady, onInitError } = options;
  if (typeof config !== 'string' && !_isObject(config)) {
    return {
      hasError: true,
      error: `'config' option must be a string or an object. Found: '${config}'`,
    };
  }
  if (!_isObject(init) || typeof init.onLoad !== 'string') {
    return {
      hasError: true,
      error: `'init' option must be an object with an 'onLoad' property. Found: '${init}'`,
    };
  }
  if (onReady && typeof onReady !== 'function') {
    return {
      hasError: true,
      error: `'onReady' option must be a function. Found: '${onReady}'`,
    };
  }
  if (onInitError && typeof onInitError !== 'function') {
    return {
      hasError: true,
      error: `'onInitError' option must be a function. Found: '${onInitError}'`,
    };
  }
  return {
    hasError: false,
    error: null,
  };
}
