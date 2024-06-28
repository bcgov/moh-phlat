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
  if (value) {
    return moment(String(value)).format('MMMM D YYYY');
  }
}

/**
 * @function formatDateLong
 * Converts a date to a 'YYYY-MM-DD hh:mm:ss a' formatted string
 * @param {Date} value A date object
 * @returns {String} A string representation of `value`
 */
export function formatDateLong(value) {
  if (value) {
    return moment(String(value)).format('YYYY-MM-DD hh:mm:ss a');
  }
}
// Function to convert object to URL query parameters
export function objectToQueryParams(obj) {
  const queryParams = [];
  for (const key in obj) {
    if (Object.hasOwnProperty.call(obj, key)) {
      if (Array.isArray(obj[key])) {
        obj[key].forEach((element) => {
          queryParams.push(
            `${encodeURIComponent(key)}=${encodeURIComponent(element)}`
          );
        });
      } else {
        queryParams.push(
          `${encodeURIComponent(key)}=${encodeURIComponent(obj[key])}`
        );
      }
    }
  }
  return queryParams.join('&');
}
