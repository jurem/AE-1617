package fib

import (
	"math"
	"math/big"
)

// Fib calculates Fibonacci sequence
type Fib interface {
	Init(n int64)            // setup the Fibonacci calculator
	NthFib(n int64) *big.Int // calculate the n-th number in Fibonacci sequence
}

// RecFibMem calculates Fibonacci sequence using recursive functions and memoisation.
type RecFibMem struct {
	mem []*big.Int
}

// Init implementation
func (r *RecFibMem) Init(n int64) {
	r.mem = make([]*big.Int, n)
}

// NthFib recursive
func (r *RecFibMem) NthFib(n int64) *big.Int {
	if n == 0 || n == 1 {
		return big.NewInt(n)
	}

	if v := r.mem[n]; v != nil {
		return v
	}

	left := r.NthFib(n - 1)
	right := r.NthFib(n - 2)

	v := new(big.Int)
	v.Add(left, right)
	r.mem[n] = v
	return v
}

func (r *RecFibMem) String() string {
	return "RecFibMem"
}

// IterFibMem calculates Fibonacci sequence using iterative approach with memoisation.
type IterFibMem struct {
	mem       []*big.Int
	solvedInd int64
}

// Init implementation
func (r *IterFibMem) Init(n int64) {
	r.mem = make([]*big.Int, n)
	r.solvedInd = 1
	r.mem[0] = new(big.Int)
	r.mem[1] = big.NewInt(1)
}

// NthFib iteratively with memoisation
func (r *IterFibMem) NthFib(n int64) *big.Int {
	if n > r.solvedInd {
		var v *big.Int
		for i := r.solvedInd + 1; i <= n; i++ {
			v = new(big.Int)
			v.Add(r.mem[i-1], r.mem[i-2])
			r.mem[i] = v
		}
		r.solvedInd = n
		return v
	}

	return r.mem[n]
}

func (r *IterFibMem) String() string {
	return "IterFibMem"
}

// GoldenRoundFib calculates fibonnaci with golden ratio
//
// doesn't work properly
type GoldenRoundFib struct {
	phi   *big.Float // golden ratio
	sfive *big.Float // sqrt(5)
}

// Init implementation
func (r *GoldenRoundFib) Init(n int64) {
	// calculate sqrt(5)
	const prec = 20000
	steps := int(math.Log2(prec))

	// Initialize values we need for the computation.
	five := new(big.Float).SetPrec(prec).SetInt64(5)
	half := new(big.Float).SetPrec(prec).SetFloat64(0.5)

	// Use 1 as the initial estimate.
	x := new(big.Float).SetPrec(prec).SetInt64(2)
	t := new(big.Float)
	for i := 0; i <= steps; i++ {
		t.Quo(five, x) // t = 2.0 / x_n
		t.Add(x, t)    // t = x_n + (2.0 / x_n)
		x.Mul(half, t) // x_{n+1} = 0.5 * t
	}
	r.sfive = x

	// golden ratio
	t.Add(x, big.NewFloat(1.0))
	t.Mul(t, big.NewFloat(0.5))

	r.phi = t
}

func power(x *big.Float, exp int64) *big.Float {
	z := big.NewFloat(1)
	y := new(big.Float).Set(x)
	for exp > 0 {
		if exp&1 == 1 {
			z.Mul(z, y)
		}
		y.Mul(y, y)
		exp >>= 1
	}
	return z
}

// NthFib by rounding golden ratio
func (r *GoldenRoundFib) NthFib(n int64) *big.Int {
	pow := power(r.phi, n)
	pow.Mul(pow, big.NewFloat(0.5))

	i := new(big.Int)
	pow.Int(i)

	return i
}

func (r *GoldenRoundFib) String() string {
	return "GoldenRoundFib"
}

// IterativeFib started out as algorithm 5.10, but since Go doesn't do native
// vectors and matrices, it was optimized into the classic iterative algorithm
type IterativeFib struct {
}

// Init implementation
func (r *IterativeFib) Init(n int64) {
}

// NthFib iteratively
func (r *IterativeFib) NthFib(n int64) *big.Int {
	a1, a2, c := big.NewInt(1), big.NewInt(1), new(big.Int)

	// since Go doesn't vectorize, we can simplify the matrix to simple iterative approach
	for i := int64(0); i < n-1; i++ {
		c.Set(a1)
		a1.Add(a1, a2)
		a2.Set(c)
	}

	return a2
}

func (r *IterativeFib) String() string {
	return "MatrixFib"
}
