import { formatDate, formatDateLong, objectToQueryParams, cleanFilter, transformSortBy } from '~/utils/filters';
import moment from 'moment';

describe('formatDate', () => {
  it('should format a valid date string correctly', () => {
    const date = '2023-10-01';
    const formattedDate = formatDate(date);
    expect(formattedDate).toBe(moment(date).format('MMMM D YYYY'));
  });

  it('should return undefined for an invalid date string', () => {
    const invalidDate = 'invalid-date';
    const formattedDate = formatDate(invalidDate);
    expect(formattedDate).toBeUndefined();
  });

  it('should return undefined when no value is passed', () => {
    const formattedDate = formatDate();
    expect(formattedDate).toBeUndefined();
  });
});

describe('formatDateLong', () => {
    it('should format a valid ISO 8601 date string correctly', () => {
      const date = '2023-10-05T14:48:00.000Z';
      const formattedDate = formatDateLong(date);
      expect(formattedDate).to.equal('2023-10-05 02:48:00 pm');
    });
  
    it('should return undefined for an invalid date string', () => {
      const date = 'invalid-date';
      const formattedDate = formatDateLong(date);
      expect(formattedDate).to.be.undefined;
    });
  
    it('should return undefined for a null value', () => {
      const formattedDate = formatDateLong(null);
      expect(formattedDate).to.be.undefined;
    });
  
    it('should return undefined for an undefined value', () => {
      const formattedDate = formatDateLong(undefined);
      expect(formattedDate).to.be.undefined;
    });
  });

// Test suite for objectToQueryParams
describe('objectToQueryParams', () => {
    it('should convert a simple flat object to query params', () => {
        const obj = { name: 'John', age: 30 };
        const queryParams = objectToQueryParams(obj);
        expect(queryParams).to.equal('name=John&age=30');
      });
    
      it('should convert a nested object to query params', () => {
        const obj = { user: { name: 'John', age: 30 } };
        const queryParams = objectToQueryParams(obj);
        expect(queryParams).to.equal('user%5Bname%5D=John&user%5Bage%5D=30');
      });
    
      it('should convert an object with arrays to query params', () => {
        const obj = { names: ['John', 'Jane'], ages: [30, 25] };
        const queryParams = objectToQueryParams(obj);
        expect(queryParams).to.equal('names%5B0%5D=John&names%5B1%5D=Jane&ages%5B0%5D=30&ages%5B1%5D=25');
      });
    
      it('should convert an object with an array of objects to query params', () => {
        const obj = { users: [{ name: 'John', age: 30 }, { name: 'Jane', age: 25 }] };
        const queryParams = objectToQueryParams(obj);
        expect(queryParams).to.equal('users%5B0%5D%5Bname%5D=John&users%5B0%5D%5Bage%5D=30&users%5B1%5D%5Bname%5D=Jane&users%5B1%5D%5Bage%5D=25');
      });
    
      it('should return an empty string for an empty object', () => {
        const obj = {};
        const queryParams = objectToQueryParams(obj);
        expect(queryParams).to.equal('');
      });
  });

// Test suite for cleanFilter
describe('cleanFilter', () => {
    it('should remove keys with empty arrays', () => {
      const filter = { a: [], b: [1, 2], c: [] };
      const cleanedFilter = cleanFilter(filter);
      expect(cleanedFilter).to.deep.equal({ b: [1, 2] });
    });
  
    it('should not remove keys with non-empty arrays', () => {
      const filter = { a: [1], b: [1, 2], c: [3] };
      const cleanedFilter = cleanFilter(filter);
      expect(cleanedFilter).to.deep.equal({ a: [1], b: [1, 2], c: [3] });
    });
  
    it('should not remove keys with no arrays', () => {
      const filter = { a: 1, b: 2, c: 3 };
      const cleanedFilter = cleanFilter(filter);
      expect(cleanedFilter).to.deep.equal({ a: 1, b: 2, c: 3 });
    });
  });
  
  // Test suite for transformSortBy
  describe('transformSortBy', () => {
    it('should transform an array of objects with key and order', () => {
      const sortBy = [{ key: 'name', order: 'asc' }, { key: 'age', order: 'desc' }];
      const transformed = transformSortBy(sortBy);
      expect(transformed).to.deep.equal({ name: 'asc', age: 'desc' });
    });
  
    it('should ignore objects missing key or order', () => {
      const sortBy = [{ key: 'name', order: 'asc' }, { key: 'age' }, { order: 'desc' }];
      const transformed = transformSortBy(sortBy);
      expect(transformed).to.deep.equal({ name: 'asc' });
    });
  
    it('should return an empty object for an empty array', () => {
      const sortBy = [];
      const transformed = transformSortBy(sortBy);
      expect(transformed).to.deep.equal({});
    });
  });