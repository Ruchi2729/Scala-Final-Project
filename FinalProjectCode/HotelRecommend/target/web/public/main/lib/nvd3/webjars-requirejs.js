/*global requirejs */

// Ensure any request for this webjar brings in jQuery.
requirejs.config({
    paths: { "nvd3": webjars.path("nvd3", "nv.d3") },
    shim: { 
        'nvd3':  { 
            'deps' : [ 'd3js'],
            'exports': 'nv'
        } 
    }
});
