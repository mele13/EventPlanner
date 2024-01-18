# Pull the base image
FROM node:18-alpine

# Set the working directory
WORKDIR /app

# Install bash
RUN apk add --no-cache bash

# Copy app dependencies to container
COPY package.json .
COPY package-lock.json .

# Install dependencies
RUN npm install

# Deploy app for local development
CMD ["npm", "start"]
