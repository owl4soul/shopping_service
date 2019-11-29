var path = require('path');

module.exports = {
    entry: './src/main/js/app.js',
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            }
        ]
    }
};


// const path = require("path");
//
// module.exports = {
//     // точка входа
//     entry: './src/main/js/app.js',
//     // выходной каталог
//     output: {
//         // объединит все .js в файл inde_bundle.js внутри каталога /build
//         path: path.join(__dirname, "."),
//         filename: "index_bundle.js"
//     },
//     // добавление лоадеров для загрузки и объединения исходных файлов
//     module: {
//         rules: [
//             {
//                 // babel-loader - для загрузки JSX/JavaScript
//                 test: /\.js$/,
//                 exclude: /node_modules/,
//                 use: {
//                     loader: "babel-loader"
//                 },
//             },
//             {
//                 // style-loader - добавление всех стилей внутрь тега документа style
//                 // css-loader - для загрузки и объединения всех .css в один
//                 test: /\.css$/,
//                 use: ["style-loader", "css-loader"]
//             }
//         ]
//     }
// };
//
// // npm install css-loader style-loader --save-dev - установка лоадеров в качестве зависимости dev