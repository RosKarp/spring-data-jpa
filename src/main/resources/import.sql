 DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE IF NOT EXISTS products (id serial, title varchar(255), price INTEGER, PRIMARY KEY (id));
INSERT INTO products (title, price) VALUES ('Bread', 10), ('Juice', 23), ('Oranges', 20), ('Milk', 18), ('Potatoes', 5);
INSERT INTO products (title, price) VALUES ('Grape', 30), ('Cheese', 52), ('Banana', 15), ('Plums', 25), ('Honey', 110);
INSERT INTO products (title, price) VALUES ('Beans', 12), ('Garlic', 31), ('Olive oil', 90), ('Strawberry', 87), ('Mint', 61);
INSERT INTO products (title, price) VALUES ('Cucumber', 22), ('Cabbage', 7), ('Chocolate', 88), ('Apricot', 32), ('Apple', 24);
