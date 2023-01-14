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
     $scope.loadCart = function () {        // ДЗ 10
         $http.get(contextPath + '/load_cart').then(function (response) {$scope.cart = response.data;});
     };
        $scope.deleteProduct = function (productId) {       // ДЗ 9
            $http.delete(contextPath + '/' + productId)
                .then(function () {
                    $scope.loadProducts();
                })
        };
     $scope.addProductToCart = function (Id, Title, Price) {       // ДЗ 10
         $http.get(contextPath + '/to_cart/' + Id + '/' + Title + '/' + Price)
             .then(function () {
                 $scope.loadCart();
             })
     };
     $scope.deleteProductFromCart = function (productId) {       // ДЗ 10
         $http.delete(contextPath + '/delete_from_cart/' + productId)
             .then(function () {
                 $scope.loadCart();
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
     $scope.loadCart();
});