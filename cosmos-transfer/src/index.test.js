const request = require('supertest');
const express = require('express');
const app = express();

const jsonFiles = {
  "README.md": "# Application\n\nThis application was generated as a starter. No business logic or specific framework was requested.\n\n- All files are represented as path-to-content JSON only.\n",
  ".gitignore": "node_modules/\ndist/\n.env\n*.log\n",
  "package.json": "{\n  \"name\": \"json-file-server\",\n  \"version\": \"1.0.0\",\n  \"main\": \"src/index.js\",\n  \"scripts\": {\n    \"start\": \"node src/index.js\",\n    \"test\": \"jest --runInBand\"\n  },\n  \"dependencies\": {\n    \"express\": \"^4.18.2\",\n    \"dotenv\": \"^16.4.5\"\n  },\n  \"devDependencies\": {\n    \"jest\": \"^29.7.0\",\n    \"supertest\": \"^6.3.3\"\n  }\n}\n",
};

app.get('/', (req, res) => {
  res.json(jsonFiles);
});

describe('GET /', () => {
  it('should return JSON file map', async () => {
    const res = await request(app).get('/');
    expect(res.statusCode).toEqual(200);
    expect(res.body)["toMatchObject"](jsonFiles);
  });
});
