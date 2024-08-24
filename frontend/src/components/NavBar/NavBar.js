import * as React from 'react'
import { Avatar, Box, Button, Container, IconButton, Menu, MenuItem, Toolbar, Typography } from '@mui/material'
import AppBar from '@mui/material/AppBar'

const pages = ['Home', 'My Wishlists']
const userOptions = ['Log Out']

function NavBar () {
  const [anchorElUser, setAnchorElUser] = React.useState(null)

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget)
  }

  const handleCloseUserMenu = (event) => {
    setAnchorElUser(null)
  }

  return (
    <AppBar id='app-bar' position='fixed' elevation={0} color='light' sx={{ py: 1 }}>
      <Container maxWidth='xl'>
        <Toolbar>
          <Typography
            variant='logo'
            component='a'
            href='#app-bar'
            sx={{
              flexGrow: 1,
              display: { md: 'flex' },
              color: 'text.dark',
              textDecoration: 'none'
            }}
          >
            WISHLIST.IO
          </Typography>

          <Box sx={{ flexGrow: 0, display: { md: 'flex' } }}>
            {pages.map((page) => (
              <Button
                key={page}
                sx={{ color: 'text.dark', display: 'block' }}
              >
                {page}
              </Button>
            ))}
          </Box>

          <Box sx={{ flexGrow: 0 }}>
            <IconButton onClick={handleOpenUserMenu}>
              <Avatar src='https://kpopping.com/documents/0f/1/800/240219-XG-Twitter-Update-Jurin-documents-1.jpeg?v=2f72c' />
            </IconButton>
            <Menu
              sx={{ mt: '45px' }}
              id='user-menu-appbar'
              anchorEl={anchorElUser}
              anchorOrigin={{
                vertical: 'top',
                horizontal: 'right'
              }}
              keepMounted
              transformOrigin={{
                vertical: 'top',
                horizontal: 'right'
              }}
              open={Boolean(anchorElUser)}
              onClose={handleCloseUserMenu}
            >
              {userOptions.map((option) => (
                <MenuItem key={option}>
                  <Typography>{option}</Typography>
                </MenuItem>
              ))}
            </Menu>
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  )
}

export default NavBar
