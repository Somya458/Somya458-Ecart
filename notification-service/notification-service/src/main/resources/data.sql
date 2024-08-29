
CREATE TABLE notification(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    message VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO notification(user_id, message, status) VALUES 
(11, 'Order confirmed', 'Pending'),
(22, 'Order prepared', 'In Progress'),
(333, 'Order delivered', 'Delivered');