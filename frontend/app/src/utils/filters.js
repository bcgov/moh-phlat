import moment from 'moment';

//
// Date format Filters {{ expression | filter }}
//

/**
 * @function formatDate
 * Converts a date to an 'MMMM D YYYY' formatted string
 * @param {Date} value A date object
 * @returns {String} A string representation of `value`
 */
export function formatDate(value) {
  if (value && moment(value, moment.ISO_8601, true).isValid()) {
    return moment(String(value)).format('MMMM D YYYY');
  }
  return undefined;
}

/**
 * @function formatDateLong
 * Converts a date to a 'YYYY-MM-DD hh:mm:ss a' formatted string
 * @param {Date} value A date object
 * @returns {String} A string representation of `value`
 */
export function formatDateLong(value) {
  if (value && moment(value, moment.ISO_8601, true).isValid()) {
    return moment.utc(String(value)).format('YYYY-MM-DD hh:mm:ss a');
  }
  return undefined;
}
// Function to convert object to URL query parameters
export function objectToQueryParams(obj) {
  const queryParams = [];

  function addQueryParam(key, value) {
    queryParams.push(`${encodeURIComponent(key)}=${encodeURIComponent(value)}`);
  }

  function processObject(prefix, obj) {
    for (const key in obj) {
      if (Object.hasOwnProperty.call(obj, key)) {
        const value = obj[key];
        const newKey = prefix ? `${prefix}[${key}]` : key;

        if (Array.isArray(value)) {
          value.forEach((element, index) => {
            if (typeof element === 'object' && element !== null) {
              processObject(`${newKey}[${index}]`, element);
            } else {
              addQueryParam(`${newKey}[${index}]`, element);
            }
          });
        } else if (typeof value === 'object' && value !== null) {
          processObject(newKey, value);
        } else {
          addQueryParam(newKey, value);
        }
      }
    }
  }

  processObject('', obj);
  return queryParams.join('&');
}

export function cleanFilter(filter) {
  Object.keys(filter).forEach((key) => {
    if (Array.isArray(filter[key]) && filter[key].length === 0) {
      delete filter[key];
    }
  });
  return filter;
}

export function transformSortBy(sortBy) {
  const transformed = {};
  sortBy.forEach((item) => {
    if (item.key && item.order) {
      transformed[item.key] = item.order;
    }
  });
  return transformed;
}
