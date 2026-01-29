#!/bin/bash

# GitHub Repository Setup Script for Vaishak
# Run this script to push to GitHub

echo "=================================="
echo "GitHub Setup for Selenium Framework"
echo "=================================="
echo ""

# Check if remote already exists
if git remote | grep -q "origin"; then
    echo "Remote 'origin' already exists. Updating..."
    git remote remove origin
fi

# Add remote (you'll need to update this with your actual repo URL)
echo "Step 1: Adding remote repository..."
echo ""
echo "NOTE: Please create a repository on GitHub first:"
echo "1. Go to https://github.com/new"
echo "2. Repository name: selenium-the-internet-framework"
echo "3. Click 'Create repository'"
echo "4. Copy the repository URL"
echo ""

# For now, use a placeholder
# Replace YOUR_USERNAME with your actual GitHub username
read -p "Enter your GitHub username: " USERNAME

REPO_URL="https://github.com/$USERNAME/selenium-the-internet-framework.git"

echo ""
echo "Adding remote: $REPO_URL"
git remote add origin $REPO_URL

# Verify remote
echo ""
echo "Verifying remote..."
git remote -v

echo ""
echo "Step 2: Pushing to GitHub..."
echo "You may be prompted for your GitHub Personal Access Token"
echo ""

# Push to main branch
git push -u origin main

echo ""
echo "=================================="
echo "Push Complete!"
echo "=================================="
echo ""
echo "Your repository is now at:"
echo "$REPO_URL"
echo ""
echo "To clone it elsewhere:"
echo "git clone $REPO_URL"
