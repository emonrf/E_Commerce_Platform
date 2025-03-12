import { useEffect, useState } from 'react'
import './App.css'
import ProductList from './ProductList';
import CategoryFilter from './CategoryFilter';
import Cart from './Cart'; // New cart component

function App() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");
  const [sortOrder, setSortOrder] = useState("asc");
  
  
  // Cart state
  const [cart, setCart] = useState([]);
  const [isCartOpen, setIsCartOpen] = useState(false);

  useEffect(() => {
    fetch('http://localhost:8080/api/products')
      .then((response) => response.json())
      .then((data) => setProducts(data));

    fetch('http://localhost:8080/api/categories')
      .then((response) => response.json())
      .then((data) => setCategories(data));
      
    // Load cart from localStorage on component mount
    const savedCart = localStorage.getItem('cart');
    if (savedCart) {
      setCart(JSON.parse(savedCart));
    }
  }, []);
  
  // Save cart to localStorage whenever it changes
  useEffect(() => {
    localStorage.setItem('cart', JSON.stringify(cart));
  }, [cart]);

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  }

  const handleSortChange = (event) => {
    setSortOrder(event.target.value);
  }

  const handleCategorySelect = (categoryId) => {
    setSelectedCategory(categoryId ? Number(categoryId) : null);
  }
  
  // Cart functions
  const addToCart = (product) => {
    setCart(prevCart => {
      // Check if product is already in cart
      const existingItem = prevCart.find(item => item.id === product.id);
      
      if (existingItem) {
        // Increase quantity if product already exists
        return prevCart.map(item => 
          item.id === product.id 
            ? { ...item, quantity: item.quantity + 1 } 
            : item
        );
      } else {
        // Add new product with quantity 1
        return [...prevCart, { ...product, quantity: 1 }];
      }
    });
  }
  
  const removeFromCart = (productId) => {
    setCart(prevCart => prevCart.filter(item => item.id !== productId));
  }
  
  const updateQuantity = (productId, newQuantity) => {
    if (newQuantity < 1) {
      removeFromCart(productId);
      return;
    }
    
    setCart(prevCart => 
      prevCart.map(item => 
        item.id === productId 
          ? { ...item, quantity: newQuantity } 
          : item
      )
    );
  }
  
  const clearCart = () => {
    setCart([]);
  }
  
  const toggleCart = () => {
    setIsCartOpen(!isCartOpen);
  }
  
  const calculateTotal = () => {
    return cart.reduce((total, item) => total + (item.price * item.quantity), 0);
  }

  const filteredProducts = products
    .filter(product => {
      return (
        (selectedCategory ? product.category.id === selectedCategory : true)
        &&
        product.name.toLowerCase().includes(searchTerm.toLowerCase())
      )
    })
    .sort((a, b) => {
      if (sortOrder === 'asc') {
        return a.price - b.price;
      } else {
        return b.price - a.price;
      }
    });

  return (
    <div className='container'>
      <div className='d-flex justify-content-between align-items-center my-4'>
        <h1>Product Catalog</h1>
        <button 
          className='btn btn-primary position-relative' 
          onClick={toggleCart}
        >

          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5M3.102 4l1.313 7h8.17l1.313-7zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4m7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4m-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2m7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2"/>
          </svg>
          
          <i className="bi bi-cart"></i> Cart
          {cart.length > 0 && (
            <span className="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
              {cart.reduce((total, item) => total + item.quantity, 0)}
            </span>
          )}
        </button>
      </div>
      
      {/* Cart sidebar */}
      <Cart 
        isOpen={isCartOpen} 
        onClose={toggleCart}
        items={cart}
        onUpdateQuantity={updateQuantity}
        onRemove={removeFromCart}
        onClear={clearCart}
        total={calculateTotal()}
      />
      
      <div className='row align-items-center mb-4'>
        <div className='col-md-3 col-sm-12 mb-2'>
          <CategoryFilter categories={categories} onSelect={handleCategorySelect} />
        </div>

        <div className='col-md-5 col-sm-12 mb-2'>
          <input
            type='text'
            className='form-control'
            placeholder='Search...'
            onChange={handleSearchChange}
          />
        </div>

        <div className='col-md-4 col-sm-12 mb-2'>
          <select className='form-select' onChange={handleSortChange}>
            <option value="asc">Sort by Price: Price Low to High</option>
            <option value="desc">Sort by Price: Price High to Low</option>
          </select>
        </div>
      </div>
      <div>
        {filteredProducts.length ? (
          // Display products with add to cart functionality
          <ProductList products={filteredProducts} onAddToCart={addToCart} />
        ) : (
          <p>No Products Found</p>
        )}
      </div>
    </div>
  )
}

export default App