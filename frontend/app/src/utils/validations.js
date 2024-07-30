const rules = (columnName = 'NaN') => ({
  required: (value) =>
    !!value || 'PHLAT.100 - ' + columnName + ' is mandatory.',
  counter: (value) => value.length <= 20 || 'Max 20 characters',
  email: (value) => {
    const pattern =
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern.test(value) || 'Invalid e-mail.';
  },
});

const validationRules = [
  {
    key: 'stakeholder',
    rules: [rules('Stakeholder').required],
  },
  {
    key: 'hdsType',
    rules: [rules('HDS Type').required],
  },
  {
    key: 'hdsName',
    rules: [rules('HDS Name').required],
  },
  {
    key: 'physicalAddr1',
    rules: [rules('Physical Addr 1').required],
  },
  {
    key: 'physicalCity',
    rules: [rules('Physical Addr City').required],
  },
];

export function applyValidationRules(nonValidatedformFieldHeaders) {
  // Create a map from formFieldHeaders for quick lookup
  const formFieldHeadersMap = validationRules.reduce((map, item) => {
    map[item.key] = item;
    return map;
  }, {});

  // Combine the arrays
  const combinedArray = nonValidatedformFieldHeaders.map((item) => {
    const correspondingFormField = formFieldHeadersMap[item.key];
    return {
      ...item,
      rules: correspondingFormField ? correspondingFormField.rules : [],
    };
  });

  return combinedArray;
}
