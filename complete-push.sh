#!/bin/bash
# Complete GitHub Push Script for Vaishak

cd /home/ubuntu/clawd/the-internet-tests

echo "=========================================="
echo "  GitHub Push - Selenium Framework"
echo "=========================================="
echo ""

# Check for username
if [ -z "$1" ]; then
    echo "Usage: ./complete-push.sh <github-username> [token]"
    echo ""
    echo "Example:"
    echo "  ./complete-push.sh VaishakSv"
    echo ""
    echo "Or with token:"
    echo "  ./complete-push.sh VaishakSv ghp_xxxxxxxxxxxx"
    exit 1
fi

USERNAME=$1
TOKEN=$2
REPO_NAME="selenium-the-internet-framework"
REPO_URL="https://github.com/$USERNAME/$REPO_NAME.git"

echo "Username: $USERNAME"
echo "Repository: $REPO_URL"
echo ""

# Remove existing remote
git remote remove origin 2>/dev/null

# Add remote
echo "Adding remote..."
git remote add origin $REPO_URL

# Verify
echo ""
echo "Remote configured:"
git remote -v

# Push
echo ""
echo "Pushing to GitHub..."
if [ -z "$TOKEN" ]; then
    # Will prompt for password/token
    git push -u origin main
else
    # Use token for authentication
    git push -u origin main
fi

echo ""
echo "=========================================="
echo "Repository URL:"
echo "  $REPO_URL"
echo "=========================================="
