# Pull the base image
FROM node:18-alpine

# Set the working directory
WORKDIR /api

# Copy app dependencies to container
COPY package.json .
COPY package-lock.json .

# Copy tsconfig.json to the container
COPY tsconfig.json .

# Install dependencies
RUN npm install && npm install typescript -D -E

# Copy the rest of the application code
COPY . .

# Build TypeScript code
RUN npm run tsc

# Deploy app for local development
CMD ["npm", "start"]
