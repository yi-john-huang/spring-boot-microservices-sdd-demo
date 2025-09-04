# Amazon Q CLI SDD Custom Agent

This directory contains templates and configuration for the SDD (Spec-Driven Development) Custom Agent for Amazon Q CLI.

## Installation

```bash
# Install the SDD agent
npx amazonq-sdd

# Start using the agent
q chat --agent sdd

# Try your first command  
"Initialize a new specification for user authentication system"
```

## Agent Configuration

The SDD Custom Agent is configured with:
- **Name**: `sdd`
- **Tools**: `fs_read`, `fs_write` 
- **Allowed Paths**: `.kiro/**`, `*.md`
- **Command Prefix**: `/kiro:`

## Available Commands

The SDD agent recognizes natural language requests for these SDD workflow actions:

| Intent | Example Usage | Description |
|--------|---------------|-------------|
| **Spec Initialization** | "Initialize a new specification for [description]" | Creates new feature specification directory and files |
| **Requirements Generation** | "Generate requirements for [feature-name]" | Creates detailed requirements document |
| **Design Creation** | "Create technical design for [feature-name]" | Generates technical design document |
| **Task Breakdown** | "Break down tasks for [feature-name]" | Creates implementation task list |
| **Implementation Guidance** | "Help me implement [feature-name]" | Provides implementation guidance |
| **Status Check** | "Show status of [feature-name]" | Displays workflow progress |
| **Project Steering** | "Set up project steering documents" | Creates project context and guidelines |
| **Custom Steering** | "Create custom steering for [area]" | Generates specialized steering documents |

## Workflow Phases

1. **Initialization** → "Initialize a new specification for [description]"
2. **Requirements** → "Generate requirements for [feature-name]" + review
3. **Design** → "Create technical design for [feature-name]" + review  
4. **Tasks** → "Break down tasks for [feature-name]" + review
5. **Implementation** → "Help me implement [feature-name]"

## File Structure

```
.kiro/
├── steering/          # Project guidelines
│   ├── product.md    # Business context
│   ├── tech.md       # Technology decisions
│   └── structure.md  # Code organization
└── specs/            # Feature specifications
    └── feature-name/
        ├── requirements.md
        ├── design.md
        ├── tasks.md
        └── spec.json
```

## Command Templates

Command behavior is defined in:
- `commands/kiro/spec-init.md`
- `commands/kiro/spec-requirements.md`
- `commands/kiro/spec-design.md`
- `commands/kiro/spec-tasks.md`
- `commands/kiro/spec-status.md`
- `commands/kiro/steering.md`

## Security Model

The SDD agent operates with restricted file system access:
- **Read Access**: Any file in project
- **Write Access**: Only `.kiro/**` and `*.md` files
- **No Network**: Agent cannot make network requests
- **No Shell**: Agent cannot execute shell commands

## Integration Notes

This Custom Agent integrates with Amazon Q CLI's native capabilities:
- Uses Amazon Q CLI's built-in file tools (`fs_read`, `fs_write`)
- Leverages Amazon Q CLI's slash command recognition
- Respects Amazon Q CLI's security and sandboxing model
- Works within Amazon Q CLI's chat interface

## Usage Examples

### Getting Started
```bash
# 1. Start a chat with the SDD agent
q chat --agent sdd

# 2. Initialize your first specification (use natural language)
"Initialize a new specification for user authentication system"

# 3. Generate requirements  
"Generate requirements for user-authentication-system"

# 4. After reviewing, create design
"Create technical design for user-authentication-system"

# 5. Generate implementation tasks
"Break down tasks for user-authentication-system"
```

### Working with Features
```bash
# Check status of specifications
"Show status of user-authentication-system"

# Get implementation help
"Help me implement user-authentication-system"

# Create project-wide guidelines
"Set up project steering documents"

# Create specialized steering
"Create custom steering for security"
```

**Important**: Use natural language in the chat - don't type literal `/kiro:` commands. The agent recognizes your intent and translates it to the appropriate SDD workflow actions.

## Customization

To modify the agent behavior:
1. Edit the command templates in `.amazonq/commands/kiro/`
2. The agent will reference your local templates for behavior
3. Templates define exactly how the agent should respond to each command type

## Support

- **GitHub**: [amazonq-spec](https://github.com/gotalab/amazonq-spec)
- **NPM Package**: [amazonq-sdd](https://www.npmjs.com/package/amazonq-sdd)
- **Documentation**: [README](../amazonq-sdd/README.md)