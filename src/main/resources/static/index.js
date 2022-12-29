 angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app/products';
     $scope.loadProducts = function () {
         $http({
             url: contextPath,
             method: 'GET',
             params: {
                 p: $scope.range ? $scope.range.p : null,
                 min: $scope.range ? $scope.range.min : null,
                 max: $scope.range ? $scope.range.max : null
             }
         }).then(function (response) {$scope.products = response.data.content;});
     };
        $scope.deleteProduct = function (productId) {
            $http.get(contextPath + '/delete/' + productId)
                .then(function (response) {
                    $scope.loadProducts();
                })
        };
    $scope.loadProducts();
});