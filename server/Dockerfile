# Pull the base image
FROM node:18-alpine

# Set the working directory
WORKDIR /api

# Copy app dependencies to container
COPY package.json .
COPY package-lock.json .

# Install dependencies
RUN npm install

COPY . .

# Deploy app for local development
CMD ["npm", "start"]
