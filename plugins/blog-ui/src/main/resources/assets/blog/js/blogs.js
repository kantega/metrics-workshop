angular.module("blogs", ["ngRoute"])
    .controller("CreateBlogController", ["$scope", "$http", "$location", function ($scope, $http, $location) {

        $scope.addBlog = function (blog) {
            $http.post("/r/blogs", blog)
                .success(function (data) {
                    $location.path("blogs/" + blog.name);
                })
        }
    }])
    .controller("ListBlogsController", ["$scope", "$rootScope", "$http", "$location", function ($scope, $rootScope, $http, $location) {

        $rootScope.backStyle = {};
        $scope.encodeURIComponent = encodeURIComponent;

        $http.get("/r/blogs")
            .success(function (data) {
                $scope.blogs = data;
            })

    }])

    .controller("BlogController", ["$scope", "$rootScope", "$http", "$location", "$routeParams", "$sce", function ($scope, $rootScope, $http, $location, $routeParams, $sce) {

        $scope.post = {
            title: "My new blogpost",
            content: "Something __really__ weird happend today on my way to school:\n\n" +
                "* First I fell off my bike\n" +
                "* Then my phone started ringing..\n"
        };

        $http.get("/r/blogs/" + $routeParams.blogId)
            .success(function (data) {
                $scope.blog = data;
                $rootScope.backStyle = {'background-color': data.color};
            });

        $http.get("/r/blogs/" + $routeParams.blogId +"/posts")
            .success(function (data) {
                $scope.blogPosts = data;
            })


        $scope.markdown = function(content) {
            return $sce.trustAsHtml(markdown.toHTML(content));
        };

        $scope.addPost = function(post) {
            $http.post("/r/blogs/" +$scope.blog.name +"/posts", post)
                .success(function (data) {
                    $location.path("/blogs/" + $scope.blog.name +"/" + $scope.post.title);
                })
        }

    }])
    .controller("BlogPostController", ["$scope", "$rootScope", "$http", "$location", "$routeParams", "$sce", function ($scope, $rootScope, $http, $location, $routeParams, $sce) {


        function loadData() {
            $http.get("/r/blogs/" + $routeParams.blogId)
                .success(function (data) {
                    $scope.blog = data;
                    $rootScope.backStyle = {'background-color': data.color};
                });

            $http.get("/r/blogs/" + $routeParams.blogId +"/" + $routeParams.blogPost)
                .success(function (data) {
                    $scope.post = data;
                });


            $http.get("/r/blogs/" + $routeParams.blogId +"/" + $routeParams.blogPost +"/comments")
                .success(function (data) {
                    $scope.comments = data;
                });
        }

        loadData();


        $scope.markdown = function(content) {
            return  content ? $sce.trustAsHtml(markdown.toHTML(content)) :undefined;

        };

        $scope.comment = {author: "John Doe", content: "I really likez..."};

        $scope.addComment = function(blog, post, comment) {
            $http.post("/r/blogs/" +blog.name +"/" + post.title + "/comments" , comment)
                .success(function (data) {
                    loadData();
                })
        }

    }])
    .config(function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/blogs', {
                templateUrl: 'assets/blog/partials/blogs.html',
                controller: 'ListBlogsController'
            }).when('/blogs/:blogId', {
                templateUrl: 'assets/blog/partials/blog.html',
                controller: 'BlogController'
            }).when('/blogs/:blogId/:blogPost', {
                templateUrl: 'assets/blog/partials/blogpost.html',
                controller: 'BlogPostController'
            }).otherwise({
                redirectTo: "/blogs"
            })
    });