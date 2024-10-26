export const errorMessages = {
    required: (field, customMessage = false) => {
        let prefix = 'This field';
        if ('string' === typeof field && field.length > 0) {
            if (customMessage) {
                return field;
            }
            prefix = field;
        }
        return `${ prefix } cannot be empty`;
    },
    type: {
        email: 'Invalid E-Mail',
        phone: 'Invalid phone number',
        number: 'Input must be a number',
    },
};
