insert into products(product_id, product_name)
values (1, 'Producto 1'),
       (2, 'Producto 2'),
       (3, 'Producto 3'),
       (4, 'Producto 4'),
       (5, 'Producto 5');

insert into categories(category_id, category_name)
values (1, 'Categoría A'),
       (2, 'Categoría B'),
       (3, 'Categoría C'),
       (4, 'Categoría D'),
       (5, 'Categoría E');

insert into categories_products(product_id, category_id)
values (1,1),
       (1,2),
       (1,3),
       (1,4),
       (1,5),
       (2,1),
       (2,3),
       (3,4),
       (3,5),
       (4,1),
       (5,5);

