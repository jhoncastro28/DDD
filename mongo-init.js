/**
 * MongoDB initialization script to prepare the microservice database.
 * Creates the app_users collection and enforces a unique userId index.
 */
const database = 'microservice';
const collection = 'app_users';

db = db.getSiblingDB(database);

if (!db.getCollectionNames().includes(collection)) {
  db.createCollection(collection);
}

db.getCollection(collection).createIndex(
  { userId: 1 },
  {
    name: 'userId_unique',
    unique: true,
    background: false,
  },
);
