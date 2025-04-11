CREATE TABLE IF NOT EXISTS GASEOSAS_ENTITY (
                                    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
                                    GaseosaName VARCHAR(255) NOT NULL,
                                    GaseosaSabor VARCHAR(255) NOT NULL,
                                    Empresa VARCHAR(255) NOT NULL
);
