# URL Shortener

A Spring Boot REST API for shortening URLs with analytics and management features.

## Features

- Create short URLs from long URLs
- Redirect to original URLs via short codes
- Update and delete existing URLs
- Access statistics tracking
- URL metadata retrieval

## Quick Start

1. Start MySQL database:

```bash
docker-compose up -d
```

2. Run the application:

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## Configuration

Key configuration in `application.yml`:

- **Database**: MySQL connection settings
- **Short URL length**: Configurable via `shortener.code-length` (default: 6)
- **Collision retry attempts**: Configurable via `shortener.max-tries` (default: 3)
- **Base URL**: Configurable via `shortener.base-url`

## API Endpoints

| Method | Endpoint                                           | Description              |
|--------|----------------------------------------------------|--------------------------|
| POST   | [`/api/v1/shorten`](#create-short-url)             | Create Short URL         |
| GET    | [`/{code}`](#redirect-to-original-url)             | Redirect to Original URL |
| GET    | [`/api/v1/urls/{code}`](#get-url-metadata)         | Get URL Metadata         |
| PATCH  | [`/api/v1/urls/{code}`](#update-short-url)         | Update Short URL         |
| DELETE | [`/api/v1/urls/{code}`](#delete-short-url)         | Delete Short URL         |
| GET    | [`/api/v1/urls/{code}/stats`](#get-url-statistics) | Get URL Statistics       |

---

### Create Short URL

**POST** `/api/v1/shorten`

**Request Body:**

```json
{
  "url": "https://example.com/very/long/url"
}
```

**Response `201 Created`:**

```json
{
  "code": "abc123",
  "shortUrl": "http://localhost:8080/abc123"
}
```

### Redirect to Original URL

**GET** `/{code}`

Returns `302 Found` redirect to the original URL and increments access count.

### Get URL Metadata

**GET** `/api/v1/urls/{code}`

**Response `200 OK`:**

```json
{
  "code": "abc123",
  "shortUrl": "http://localhost:8080/abc123",
  "originalUrl": "https://example.com/very/long/url",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

### Update Short URL

**PATCH** `/api/v1/urls/{code}`

**Request Body:**

```json
{
  "url": "https://updated-example.com"
}
```

**Response `200 OK`:**

```json
{
  "code": "abc123",
  "shortUrl": "http://localhost:8080/abc123"
}
```

### Delete Short URL

**DELETE** `/api/v1/urls/{code}`

Returns `204 No Content` on successful deletion.

### Get URL Statistics

**GET** `/api/v1/urls/{code}/stats`

**Response `200 OK`:**

```json
{
  "code": "abc123",
  "accessCount": 42
}
```

## Error Responses

The API returns appropriate status codes and error messages:

- `400 Bad Request` - Invalid URL format or validation errors
- `404 Not Found` - Short URL code does not exist
