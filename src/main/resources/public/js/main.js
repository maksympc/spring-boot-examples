var app = angular.module("springbootDemo", []);

app.controller("AppCtrl", function ($scope, $http) {
    $scope.websites = [];

    $http.get("http://localhost:8090/api/stackoverflow").then(function (data) {
        $scope.websites = data.data;
    });
});
