ALTER TABLE usuarios
ADD CONSTRAINT unique_email UNIQUE (email);
