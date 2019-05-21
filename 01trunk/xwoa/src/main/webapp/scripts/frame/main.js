/**
 * Created by zk on 14-4-8.
 */

require.config({
    baseUrl: '../scripts',

    paths: {
		'jquery': 'jquery-1.7.2.min',
        'angular': 'frame/components/angular/angular.min'
    },

    shim: {
        angular: {
            exports: 'angular'
        }
    }
});

require(['angular'], function(angular) {
    console.log(angular);
});