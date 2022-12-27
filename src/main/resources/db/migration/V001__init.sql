CREATE TABLE IF NOT EXISTS "book" (
  "id" UUID NOT NULL,
  "title" VARCHAR(80) NOT NULL,
  "author" VARCHAR(80) NOT NULL,
  "published_on" DATE NOT NULL,
  CONSTRAINT book_pkey PRIMARY KEY (id)
  );