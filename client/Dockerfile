# Pull the base image
FROM node:18-alpine as build-stage

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the entire project
COPY . .

# Build the Vue.js application
RUN npm run build-only

# Stage 2: Serve the Vue.js application using nginx
FROM nginx:alpine as production-stage

# Copy the build application from the previous stage to the Nginx container
COPY --from=build-stage /app/dist /usr/share/nginx/html

# Copy the nginx configuration file
COPY ./nginx.conf /etc/nginx/nginx.conf

# Expose port 5173
EXPOSE 5173

# Start nginx server
CMD ["nginx", "-g", "daemon off;"]
