CREATE TABLE store_items (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    size VARCHAR(10) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    color VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    is_for_swimming BOOLEAN,
    has_chest_pocket BOOLEAN
);