define(function() {
    return  {
        undefined: function (variable) {
            return typeof variable == 'undefined';
        },
        'null': function (variable) {
            return variable == null;
        },
        boolean: function (variable) {
            return typeof variable == 'boolean';
        },
        number: function (variable) {
            return typeof variable == 'number';
        },
        string: function (variable) {
            return typeof variable == 'string';
        },
        'function': function (variable) {
            return typeof variable == 'function';
        },
        object: function (variable) {
            return variable != null && typeof variable == 'object';
        }
    };
});