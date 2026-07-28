CREATE TABLE categorie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    image VARCHAR(255) NOT NULL
);
INSERT INTO categorie (nom, image) VALUES
('Fruits & Veges', 'images/icon-vegetables-broccoli.png'),
('Breads & Sweets', 'images/icon-bread-baguette.png'),
('Soft Drinks', 'images/icon-soft-drinks-bottle.png'),
('Wine & Spirits', 'images/icon-wine-glass-bottle.png'),
('Meat & Poultry', 'images/icon-animal-products-drumsticks.png'),
('Flour & Baking', 'images/icon-bread-herb-flour.png'),
('Fresh Vegetables', 'images/icon-vegetables-broccoli.png'),
('Organic Veges', 'images/icon-vegetables-broccoli.png'),
('Local Products', 'images/icon-vegetables-broccoli.png'),
('Imported Fruits', 'images/icon-vegetables-broccoli.png'),
('Seasonal Fruits', 'images/icon-vegetables-broccoli.png'),
('Healthy Greens', 'images/icon-vegetables-broccoli.png');
