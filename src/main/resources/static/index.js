 angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/api/v1/products';
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
        $scope.deleteProduct = function (productId) {       // ДЗ 9
            $http.delete(contextPath + '/' + productId)
                .then(function () {
                    $scope.loadProducts();
                })
        };
        $scope.newProduct = function () {           // ДЗ 9
            if ($scope.newProdDto.id != null) {             // разделение save or update
                $http.put(contextPath, $scope.newProdDto).then(function () {
                    $scope.loadProducts();
                });
            } else {
                $http.post(contextPath, $scope.newProdDto).then(function () {
                    $scope.loadProducts();
                });
            }
        };
    $scope.loadProducts();
});