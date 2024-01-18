# Pull the base image
FROM node:18-alpine

# Set the working directory
WORKDIR /api

# Install bash
RUN apk add --no-cache bash

# Copy app dependencies to container
COPY package.json .
COPY package-lock.json .

# Copy tsconfig.json to the container
COPY tsconfig.json .

# Install dependencies
RUN npm install && npm install typescript -D -E
# RUN npm install && npm install typescript ts-node ts-node-dev typeorm reflect-metadata dotenv -D -E -g
# RUN npm install pg pg-promise -E -g
# RUN npm --save install express -E # Da problemas express
# RUN npm i --save @types/express -D # Da problemas express

# Copy the rest of the application code
COPY . .

# Build TypeScript code
RUN npm run tsc

# Deploy app for local development
CMD ["npm", "start"]
# CMD ["npm", "run", "dev2"]
